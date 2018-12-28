$(document).ready(function () {

    var s = {
        "async": true,
        "crossDomain": true,
        "url": "rest/getCategory",
        "method": "GET",
        "headers": {
        }
    };
    $.ajax(s).done(function (response) {
        var data= "<div class=\"dropdown-content\">";
        for(var i=0;i<response.length;i++) {
            data += "<a class=\"dropdown-item\" value=\""+response[i].id+"\">"+response[i].name+"</a>";
        }
        data += "</div>"
        $('.dropdown-content').replaceWith(data);
        getCategory(parseInt(3));
    });

    $("#streams").change(function() {
        var stream_id = $(this).val(),
            curtime = player.getTime();
        console.log("stream changed: stream_id=" + stream_id + " time=" + curtime);
        if(lastPlaybackSession) {
            var params = {};
            $.extend(params, lastPlaybackSession);
            params.stream_id = stream_id;
            startPlaybackSession(params);
        }
    });


    $('body').on("click", ".dropdown-item", function (e) {
        getCategory(parseInt(e.currentTarget.attributes.value.value));
    });

     $('body').on("click", ".item", function (e) {
     console.log(e.currentTarget.attributes.value.value);

         var s = {
             "async": true,
             "crossDomain": true,
             "url": "rest/getChannel",
             "method": "GET",
             "headers": {
                 "channelId":e.currentTarget.attributes.value.value
             }
         };
         $.ajax(s).done(function (response) {
             console.log(response);
             initPlaybackSession({
                 mode: "ace",
                 transport_file_url: response.source
             });
         });




         $('#creatAndAddOrderToTable').modal({
             backdrop: 'static',
             keyboard: false
         }, 'show');
         // $('#editProductInOrder').modal('hide');
    });
});

$('body').on("click", ".editProductInOrder", function (e) {



});





function update() {
    (function () {

        'use strict';

        // iPad and iPod detection
        var isiPad = function(){
            return (navigator.platform.indexOf("iPad") != -1);
        };

        var isiPhone = function(){
            return (
                (navigator.platform.indexOf("iPhone") != -1) ||
                (navigator.platform.indexOf("iPod") != -1)
            );
        };

        // Click outside of offcanvass
        var mobileMenuOutsideClick = function() {
            $(document).click(function (e) {
                var container = $("#fh5co-offcanvass, .js-fh5co-menu-btn");
                if (!container.is(e.target) && container.has(e.target).length === 0) {
                    if ( $('#fh5co-offcanvass').hasClass('fh5co-awake') ) {
                        $('#fh5co-offcanvass').removeClass('fh5co-awake');
                    }
                }
            });

            $(window).scroll(function(){
                if ( $(window).scrollTop() > 500 ) {
                    if ( $('#fh5co-offcanvass').hasClass('fh5co-awake') ) {
                        $('#fh5co-offcanvass').removeClass('fh5co-awake');
                    }
                }
            });
        };

        // Magnific Popup

        var magnifPopup = function() {
            $('.image-popup').magnificPopup({
                type: 'image',
                removalDelay: 300,
                mainClass: 'mfp-with-zoom',
                titleSrc: 'title',
                gallery:{
                    enabled:true
                },
                zoom: {
                    enabled: true, // By default it's false, so don't forget to enable it

                    duration: 300, // duration of the effect, in milliseconds
                    easing: 'ease-in-out', // CSS transition easing function

                    // The "opener" function should return the element from which popup will be zoomed in
                    // and to which popup will be scaled down
                    // By defailt it looks for an image tag:
                    opener: function(openerElement) {
                        // openerElement is the element on which popup was initialized, in this case its <a> tag
                        // you don't need to add "opener" option if this code matches your needs, it's defailt one.
                        return openerElement.is('img') ? openerElement : openerElement.find('img');
                    }
                }
            });
        };


        var animateBoxWayPoint = function() {

            if ($('.animate-box').length > 0) {
                $('.animate-box').waypoint( function( direction ) {

                    if( direction === 'down' && !$(this).hasClass('animated') ) {
                        $(this.element).addClass('bounceIn animated');
                    }

                } , { offset: '75%' } );
            }

        };


        $(function(){
            magnifPopup();
            mobileMenuOutsideClick();
            animateBoxWayPoint();
        });


    }());

}

function size() {
    /*!
 * Salvattore 1.0.8 by @rnmp and @ppold
 * https://github.com/rnmp/salvattore
 */
    !function (e, t) {
        "function" == typeof define && define.amd ? define([], t) : "object" == typeof exports ? module.exports = t() : e.salvattore = t()
    }(this, function () {/*! matchMedia() polyfill - Test a CSS media type/query in JS. Authors & copyright (c) 2012: Scott Jehl, Paul Irish, Nicholas Zakas, David Knight. Dual MIT/BSD license */
        window.matchMedia || (window.matchMedia = function () {
            "use strict";
            var e = window.styleMedia || window.media;
            if (!e) {
                var t = document.createElement("style"), n = document.getElementsByTagName("script")[0], r = null;
                t.type = "text/css", t.id = "matchmediajs-test", n.parentNode.insertBefore(t, n), r = "getComputedStyle" in window && window.getComputedStyle(t, null) || t.currentStyle, e = {
                    matchMedium: function (e) {
                        var n = "@media " + e + "{ #matchmediajs-test { width: 1px; } }";
                        return t.styleSheet ? t.styleSheet.cssText = n : t.textContent = n, "1px" === r.width
                    }
                }
            }
            return function (t) {
                return {matches: e.matchMedium(t || "all"), media: t || "all"}
            }
        }()),/*! matchMedia() polyfill addListener/removeListener extension. Author & copyright (c) 2012: Scott Jehl. Dual MIT/BSD license */
            function () {
                "use strict";
                if (window.matchMedia && window.matchMedia("all").addListener) return !1;
                var e = window.matchMedia, t = e("only all").matches, n = !1, r = 0, a = [], i = function (t) {
                    clearTimeout(r), r = setTimeout(function () {
                        for (var t = 0, n = a.length; n > t; t++) {
                            var r = a[t].mql, i = a[t].listeners || [], o = e(r.media).matches;
                            if (o !== r.matches) {
                                r.matches = o;
                                for (var c = 0, l = i.length; l > c; c++) i[c].call(window, r)
                            }
                        }
                    }, 30)
                };
                window.matchMedia = function (r) {
                    var o = e(r), c = [], l = 0;
                    return o.addListener = function (e) {
                        t && (n || (n = !0, window.addEventListener("resize", i, !0)), 0 === l && (l = a.push({
                            mql: o,
                            listeners: c
                        })), c.push(e))
                    }, o.removeListener = function (e) {
                        for (var t = 0, n = c.length; n > t; t++) c[t] === e && c.splice(t, 1)
                    }, o
                }
            }(), function () {
            "use strict";
            for (var e = 0, t = ["ms", "moz", "webkit", "o"], n = 0; n < t.length && !window.requestAnimationFrame; ++n) window.requestAnimationFrame = window[t[n] + "RequestAnimationFrame"], window.cancelAnimationFrame = window[t[n] + "CancelAnimationFrame"] || window[t[n] + "CancelRequestAnimationFrame"];
            window.requestAnimationFrame || (window.requestAnimationFrame = function (t, n) {
                var r = (new Date).getTime(), a = Math.max(0, 16 - (r - e)), i = window.setTimeout(function () {
                    t(r + a)
                }, a);
                return e = r + a, i
            }), window.cancelAnimationFrame || (window.cancelAnimationFrame = function (e) {
                clearTimeout(e)
            })
        }(), "function" != typeof window.CustomEvent && !function () {
            "use strict";

            function e(e, t) {
                t = t || {bubbles: !1, cancelable: !1, detail: void 0};
                var n = document.createEvent("CustomEvent");
                return n.initCustomEvent(e, t.bubbles, t.cancelable, t.detail), n
            }

            e.prototype = window.Event.prototype, window.CustomEvent = e
        }();
        var e = function (e, t, n) {
            "use strict";
            var r = {}, a = [], i = [], o = [], c = function (e, t, n) {
                e.dataset ? e.dataset[t] = n : e.setAttribute("data-" + t, n)
            };
            return r.obtainGridSettings = function (t) {
                var n = e.getComputedStyle(t, ":before"), r = n.getPropertyValue("content").slice(1, -1),
                    a = r.match(/^\s*(\d+)(?:\s?\.(.+))?\s*$/), i = 1, o = [];
                return a ? (i = a[1], o = a[2], o = o ? o.split(".") : ["column"]) : (a = r.match(/^\s*\.(.+)\s+(\d+)\s*$/), a && (o = a[1], i = a[2], i && (i = i.split(".")))), {
                    numberOfColumns: i,
                    columnClasses: o
                }
            }, r.addColumns = function (e, n) {
                for (var a, i = r.obtainGridSettings(e), o = i.numberOfColumns, l = i.columnClasses, s = new Array(+o), u = t.createDocumentFragment(), d = o; 0 !== d--;) a = "[data-columns] > *:nth-child(" + o + "n-" + d + ")", s.push(n.querySelectorAll(a));
                s.forEach(function (e) {
                    var n = t.createElement("div"), r = t.createDocumentFragment();
                    n.className = l.join(" "), Array.prototype.forEach.call(e, function (e) {
                        r.appendChild(e)
                    }), n.appendChild(r), u.appendChild(n)
                }), e.appendChild(u), c(e, "columns", o)
            }, r.removeColumns = function (n) {
                var r = t.createRange();
                r.selectNodeContents(n);
                var a = Array.prototype.filter.call(r.extractContents().childNodes, function (t) {
                    return t instanceof e.HTMLElement
                }), i = a.length, o = a[0].childNodes.length, l = new Array(o * i);
                Array.prototype.forEach.call(a, function (e, t) {
                    Array.prototype.forEach.call(e.children, function (e, n) {
                        l[n * i + t] = e
                    })
                });
                var s = t.createElement("div");
                return c(s, "columns", 0), l.filter(function (e) {
                    return !!e
                }).forEach(function (e) {
                    s.appendChild(e)
                }), s
            }, r.recreateColumns = function (t) {
                e.requestAnimationFrame(function () {
                    r.addColumns(t, r.removeColumns(t));
                    var e = new CustomEvent("columnsChange");
                    t.dispatchEvent(e)
                })
            }, r.mediaQueryChange = function (e) {
                e.matches && Array.prototype.forEach.call(a, r.recreateColumns)
            }, r.getCSSRules = function (e) {
                var t;
                try {
                    t = e.sheet.cssRules || e.sheet.rules
                } catch (n) {
                    return []
                }
                return t || []
            }, r.getStylesheets = function () {
                var e = Array.prototype.slice.call(t.querySelectorAll("style"));
                return e.forEach(function (t, n) {
                    "text/css" !== t.type && "" !== t.type && e.splice(n, 1)
                }), Array.prototype.concat.call(e, Array.prototype.slice.call(t.querySelectorAll("link[rel='stylesheet']")))
            }, r.mediaRuleHasColumnsSelector = function (e) {
                var t, n;
                try {
                    t = e.length
                } catch (r) {
                    t = 0
                }
                for (; t--;) if (n = e[t], n.selectorText && n.selectorText.match(/\[data-columns\](.*)::?before$/)) return !0;
                return !1
            }, r.scanMediaQueries = function () {
                var t = [];
                if (e.matchMedia) {
                    r.getStylesheets().forEach(function (e) {
                        Array.prototype.forEach.call(r.getCSSRules(e), function (e) {
                            try {
                                e.media && e.cssRules && r.mediaRuleHasColumnsSelector(e.cssRules) && t.push(e)
                            } catch (n) {
                            }
                        })
                    });
                    var n = i.filter(function (e) {
                        return -1 === t.indexOf(e)
                    });
                    o.filter(function (e) {
                        return -1 !== n.indexOf(e.rule)
                    }).forEach(function (e) {
                        e.mql.removeListener(r.mediaQueryChange)
                    }), o = o.filter(function (e) {
                        return -1 === n.indexOf(e.rule)
                    }), t.filter(function (e) {
                        return -1 == i.indexOf(e)
                    }).forEach(function (t) {
                        var n = e.matchMedia(t.media.mediaText);
                        n.addListener(r.mediaQueryChange), o.push({rule: t, mql: n})
                    }), i.length = 0, i = t
                }
            }, r.rescanMediaQueries = function () {
                r.scanMediaQueries(), Array.prototype.forEach.call(a, r.recreateColumns)
            }, r.nextElementColumnIndex = function (e, t) {
                var n, r, a, i = e.children, o = i.length, c = 0, l = 0;
                for (a = 0; o > a; a++) n = i[a], r = n.children.length + (t[a].children || t[a].childNodes).length, 0 === c && (c = r), c > r && (l = a, c = r);
                return l
            }, r.createFragmentsList = function (e) {
                for (var n = new Array(e), r = 0; r !== e;) n[r] = t.createDocumentFragment(), r++;
                return n
            }, r.appendElements = function (e, t) {
                var n = e.children, a = n.length, i = r.createFragmentsList(a);
                Array.prototype.forEach.call(t, function (t) {
                    var n = r.nextElementColumnIndex(e, i);
                    i[n].appendChild(t)
                }), Array.prototype.forEach.call(n, function (e, t) {
                    e.appendChild(i[t])
                })
            }, r.prependElements = function (e, n) {
                var a = e.children, i = a.length, o = r.createFragmentsList(i), c = i - 1;
                n.forEach(function (e) {
                    var t = o[c];
                    t.insertBefore(e, t.firstChild), 0 === c ? c = i - 1 : c--
                }), Array.prototype.forEach.call(a, function (e, t) {
                    e.insertBefore(o[t], e.firstChild)
                });
                for (var l = t.createDocumentFragment(), s = n.length % i; 0 !== s--;) l.appendChild(e.lastChild);
                e.insertBefore(l, e.firstChild)
            }, r.registerGrid = function (n) {
                if ("none" !== e.getComputedStyle(n).display) {
                    var i = t.createRange();
                    i.selectNodeContents(n);
                    var o = t.createElement("div");
                    o.appendChild(i.extractContents()), c(o, "columns", 0), r.addColumns(n, o), a.push(n)
                }
            }, r.init = function () {
                var e = t.createElement("style");
                e.innerHTML = "[data-columns]::before{display:block;visibility:hidden;position:absolute;font-size:1px;}", t.head.appendChild(e);
                var n = t.querySelectorAll("[data-columns]");
                Array.prototype.forEach.call(n, r.registerGrid), r.scanMediaQueries()
            }, r.init(), {
                appendElements: r.appendElements,
                prependElements: r.prependElements,
                registerGrid: r.registerGrid,
                recreateColumns: r.recreateColumns,
                rescanMediaQueries: r.rescanMediaQueries,
                init: r.init,
                append_elements: r.appendElements,
                prepend_elements: r.prependElements,
                register_grid: r.registerGrid,
                recreate_columns: r.recreateColumns,
                rescan_media_queries: r.rescanMediaQueries
            }
        }(window, window.document);
        return e
    });
}

function getCategory(group) {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "rest/category",
        "method": "GET",
        "headers": {
            "group": group
        }
    };
    $.ajax(settings).done(function (response) {
        var data= "<div id=\"fh5co-board\" data-columns>";
        console.log(response);
        for(var i=0;i<response.length;i++) {
            data += "<div class=\"item\"  value=\"" + response[i].channelNumber + "\">\n" +
                "        <div class=\"animate-box\" >\n" +
                "        <a class=\"fh5co-board-img\"><img src=\"http://1ttv.org/uploads/" + response[i].logo + "\"  height=\"42\" width=\"84\">" + "</a>\n" +
                "        </div>\n" +
                "<div class=\"fh5co-desc\">"+ response[i].name+"</div>" +
                "</div>";
        }
        data += "</div>";
        $('#fh5co-board').replaceWith(data);
        size();
        update();
    });
}