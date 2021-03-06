(ns paradiso.core
  (:require
    [cljs.inferno])
  (:require-macros
    [paradiso.dom :refer [define-tags define-svg-tags]]))

(enable-console-print!)

(def flags
  {:text 1
   :html-element (bit-shift-left 1 1)
   :component-class (bit-shift-left 1 2)
   :component-function (bit-shift-left 1 3)
   :component-unknown (bit-shift-left 1 4)
   :has-keyed-children (bit-shift-left 1 5)
   :has-non-keyed-children (bit-shift-left 1 6)
   :svg-element (bit-shift-left 1 7)
   :media-element (bit-shift-left 1 8)
   :input-element (bit-shift-left 1 9)
   :textarea-element (bit-shift-left 1 10)
   :select-element (bit-shift-left 1 11)
   :void (bit-shift-left 1 12)})

; Inferno.createVNode()
;   flags, type, [props], [...children], [events], [key], [ref],[isNormalized]

(define-tags
    a abbr address area article aside audio b base bdi bdo big blockquote body br
    button canvas caption cite code col colgroup data datalist dd del details dfn
    div dl dt em embed fieldset figcaption figure footer form h1 h2 h3 h4 h5 h6
    head header hr html i iframe img ins kbd keygen label legend li link main
    mark menu menuitem  meter nav noscript object ol optgroup option output
    p param pre progress q rp rt ruby s samp script section small source
    span strong style sub summary sup table tbody td tfoot th thead
    title tr track u ul var video wbr)

; map meta time

(define-svg-tags
  circle clipPath defs ellipse g image line
  linearGradient  path pattern polygon polyline radialGradient rect stop svg
  text tspan)

  ; mask)

(defn component [component props hooks]
  (let [hooks (clj->js
                (merge {:onComponentShouldUpdate (fn [last next] (not= last next))}
                  hooks))]
    (js/Inferno.createVNode 8 component (clj->js props) nil nil nil (clj->js hooks))))

(defn- builder [type name props & [children]]
  (js/Inferno.createVNode (get flags type) name (clj->js props) (clj->js children) nil nil nil))

(defn textarea [props & children]
  (builder :textarea-element "textarea" props children))

(defn input [props & [children]]
  (builder :input-element "input" props children))

(defn select [props & [children]]
  (builder :select-element "select" props children))

(defn text-tag [props & children]
  (builder :text "span" props children))
