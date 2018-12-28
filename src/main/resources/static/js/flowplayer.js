var engineCommandURL = null;
var lastPlaybackSession = null;
var skipReloadOnStop = false;
var player = null;
var player_id = Math.random();
var flowplayerUrl = "http://static.acestream.org/swf/flowplayer/flowplayer-3.2.18.swf";
var flashlsUrl = "http://static.acestream.org/swf/flowplayer/plugins/ace/debug/flashlsFlowPlayer.swf?" + Math.random();

function install_player() {
    // install flowplayer into container
    player = $f("player", flowplayerUrl, {
        plugins: {
            flashls: {
                url: flashlsUrl,
                hls_debug: false,
                hls_debug2: false,
                hls_lowbufferlength: 4,
                hls_minbufferlength: 35,
                hls_maxbufferlength: 80,
            },
            controls: {
                stop: true,
                time: false,
            },
        },
        playlist: [],
        canvas: {
            backgroundGradient: "none",
            backgroundColor: "#000000"
        },
        // events
        onBeforeBegin: function() {
            console.log("onBeforeBegin");
        },
        onBegin: function() {
            console.log("onBegin");
        },
        onLastSecond: function() {
            console.log("onLastSecond");
        },
        onBeforeFinish: function() {
            console.log("onBeforeFinish");
        },
        onFinish: function() {
            console.log("onFinish");
        },
        onBufferEmpty: function() {
            console.log("onBufferEmpty");
        },
        onBufferFull: function() {
            console.log("onBufferFull");
        },
        onError: function(errcode, errmsg) {
            console.log("onError: code=" + errcode + " msg=" + errmsg);
            if(errcode == 201) {
                sendEngineCommand({
                        method: "stop"
                });
                reloadFlowPlayer();
                if(errmsg && errmsg.indexOf("demuxer failed") !== -1) {
                    alert("Demuxer failed");
                }
            }
        },
        onStop: function() {
            try {
                console.log("onStop: skipReloadOnStop=" + skipReloadOnStop + " isLoaded=" + player.isLoaded());
                if(skipReloadOnStop) {
                    // need to skip once so reset flag now
                    skipReloadOnStop = false;
                }
                else {
                    sendEngineCommand({
                            method: "stop"
                    });
                    reloadFlowPlayer();
                    $("#streams").hide();
                }
            }
            catch(e) {
                console.log("error: " + e);
            }
        }
    });
    
    window.acestream_flashls_callback = function(data) {
        console.log("got response from engine", data);
        if(data.error) {
            if(data.extra_data && data.extra_data.reason === "missing_option") {
                alert("Missing option: " + data.extra_data.option);
            }
            else {
                alert(data.error);
            }
        }
        else if(data.response) {
            if(data.response.command_url) {
                engineCommandURL = data.response.command_url;
                console.log("acestream_flashls_callback: set engine command url: " + engineCommandURL);
            }
            if(data.response.event_url) {
                console.log("acestream_flashls_callback: start engine event listener on " + data.response.event_url);
                startEventListener(data.response.event_url);
            }
            if(data.response.streams) {
                got_streams(data.response.streams);
            }
        }
    }
}

function reloadFlowPlayer() {
    console.log("reloadFlowPlayer");
    if(player && player.isLoaded()) {
        player.unload();
        player.load(function() {
                if(lastPlaybackSession) {
                    var item = {
                        url: lastPlaybackSession.playbackURL,
                        acestreamURL: lastPlaybackSession.startURL,
                        provider: "flashls",
                        urlResolvers: ["flashls"],
                        autoPlay: false,
                        autoBuffering: false
                    };
                    player.setClip(item);
                }
        });
    }
}

function initPlaybackSessionFromId() {
    initPlaybackSession({
            mode: "ace",
            contentId: $("#source-id").val()
    });
}

function initPlaybackSessionFromTransportFileURL() {
    initPlaybackSession({
            mode: "ace",
            transport_file_url: $("#source-transport-file-url").val()
    });
}

function initPlaybackSessionFromInfohash() {
    var input = $("#source-infohash").val().trim();
    if(input.indexOf("magnet:") === 0) {
        // retrieve infohash from magnet
        var re = /urn:btih:([0-9a-f]{40})/i;
        var matches = input.match(re);
        if(!matches) {
            alert("Malformed magnet link");
            return;
        }
        input = matches[1];
    }
    
    initPlaybackSession({
            mode: "ace",
            infohash: input
    });
}

function initPlaybackSessionFromHlsUrl() {
    initPlaybackSession({
            mode: "hls",
            url: $("#source-url").val()
    });
}

function initPlaybackSessionFromYoutubeId() {
    initPlaybackSession({
            mode: "ace",
            url: $("#source-youtube-id").val(),
            provider: "youtube"
    });
}

function initPlaybackSession(source) {
    console.log("initPlaybackSession", source);
    
    if(!source.mode) {
        console.log("missing source mode");
        return;
    }
    
    if(source.mode !== "ace" && source.mode !== "hls") {
        console.log("unknown mode: " + source.mode);
        return;
    }
    
    var params = {
        sid: player_id,
        transcode_audio: 1,
        transcode_mp3: 0,
        use_api_events: 1,
        use_api_stop: 1,
        use_timeshift: 1,
        use_stop_notifications: 1
    };
    
    if(source.contentId) {
        params.id = source.contentId;
    }
    else if(source.transport_file_url) {
        params.url = source.transport_file_url;
    }
    else if(source.infohash) {
        params.infohash = source.infohash;
    }
    else if(source.url) {
        params.direct_url = source.url;
        if(source.provider) {
            params.provider = source.provider;
        }
        params.tracker = "udp://77.120.101.26:2710/announce";
    }
    else {
        console.log("initPlaybackSession: missing source");
        return;
    }
    startPlaybackSession(source.mode, params);
}

function startPlaybackSession(mode, params) {
    if(player && player.isLoaded()) {
        player.unload();
    }
    
    if(mode !== "hls" && mode !== "ace") {
        mode = "ace";
    }
    var startFromPlayer = true,
        paramsString = $.param(params),
        playbackURL = "http://" + ENGINE_HOST + ":" + ENGINE_PORT + "/" + mode + "/manifest.m3u8?" + paramsString,
        startURL = "http://" + ENGINE_HOST + ":" + ENGINE_PORT + "/" + mode + "/manifest.m3u8?format=json&" + paramsString;
        
    console.log("startPlaybackSession: playbackURL", playbackURL, "startURL", startURL);
    lastPlaybackSession = {
        playbackURL: playbackURL,
        startURL: startURL
    };
    
    if(startFromPlayer) {
        startPlayback(playbackURL, startURL);
    }
    else {
        $.ajax({
                url: "http://" + ENGINE_HOST + ":" + ENGINE_PORT + "/ace/manifest.m3u8",
                method: "GET",
                data: params,
                dataType: "json",
                success: function(response) {
                    console.log("initPlaybackSession: session started", response);
                    if(response.error) {
                        alert(response.error);
                    }
                    else if(!response.response) {
                        alert("Error: malformed response from engine");
                    }
                    else {
                        startPlayback(response.response.playback_url);
                        engineCommandURL = response.response.command_url;
                        if(response.response.event_url) {
                            startEventListener(response.response.event_url);
                        }
                        if(response.response.streams) {
                            got_streams(response.response.streams);
                        }
                    }
                },
                error: function(xhr, status, error) {
                    console.log("initPlaybackSession: request failed: status=" + status + " error=" + error);
                }
        });
    }
}

function startPlayback(playbackURL, acestreamURL) {
    console.log("startPlayback: url", playbackURL, "playerLoaded", (player && player.isLoaded()));
    
    if(!player) {
        console.log("startPlayback: missing flowplayer");
        return;
    }
    
    if(!player.isLoaded()) {
        console.log("startPlayback: load flowplayer and start on success");
        player.load(function() {
                startPlayback(playbackURL, acestreamURL);
        });
        return;
    }
    
    var item = {
        url: playbackURL,
        acestreamURL: acestreamURL,
        provider: "flashls",
        urlResolvers: ["flashls"]
    };
    player.setClip(item);
    player.play(0);
}

function startEventListener(url) {
    $.ajax({
            url: url,
            method: "GET",
            dataType: "json",
            cache: false,
            success: function(response) {
                if(response.error) {
                    console.log("event listener: got error", response.error);
                }
                else {
                    var event = response.response;
                    console.log("event listener: got event", event);
                    if(typeof event !== "object") {
                        if(event.indexOf("{") === 0) {
                            event = JSON.parse(event);
                        }
                        else {
                            event = {
                                name: event,
                                params: {},
                            };
                        }
                    }
                    startEventListener(url);
                    
                    if(event.name == "missing_content") {
                        if(player) {
                            // seek to live
                            console.log("no content, seek to live");
                            // set flag to avoid reloading player on stop
                            skipReloadOnStop = true;
                            setTimeout(function() {
                                    skipReloadOnStop = false;
                            }, 5000);
                            player.stop();
                            player.play();
                        }
                    }
                    else if(event.name == "stream_restarted") {
                        if(player) {
                            skipReloadOnStop = true;
                            setTimeout(function() {
                                    skipReloadOnStop = false;
                            }, 5000);
                            player.stop();
                            player.play();
                        }
                    }
                    else if(event.name == "segmenter_failed") {
                        if(player) {
                            player.stop();
                            alert("Cannot decode stream");
                        }
                    }
                    else if(event.name == "download_stopped") {
                        if(player) {
                            player.stop();
                            if(event.params) {
                                if(event.params.reason == "missing_option") {
                                    alert("Missing option: " + event.params.option);
                                }
                            }
                        }
                    }
                }
            },
            error: function(xhr, status, error) {
                console.log("event listener error: status=" + status + " error=" + error);
            }
    });
}

function sendEngineCommand(params) {
    if(!engineCommandURL) {
        console.log("sendEngineCommand: missing engine command URL");
        return;
    }
    
    $.ajax({
            url: engineCommandURL,
            method: "GET",
            data: params,
            dataType: "json",
            success: function(response) {
                console.log("sendEngineCommand: response", response);
                
            },
            error: function(xhr, status, error) {
                console.log("sendEngineCommand: request failed: status=" + status + " error=" + error);
            }
    });
}

function got_streams(streams) {
    console.log("got_streams", streams);
    $("#streams").empty();
    for(var stream_id in streams) {
        var s = streams[stream_id];
        $("#streams").append('<option value="' + s.id + '">' + s.name + '</option>');
    }
    $("#streams").show();
}



