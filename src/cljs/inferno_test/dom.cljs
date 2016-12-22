(ns inferno-test.dom)

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

; (defn valid-children [children]
;   (cond
;     (nil? children) nil
;     (string? children) children
;     (vector? children) #js children
;     (map? children) #js children))

(defn- builder [type name props & [children]]
  "props can be children, events, key or ref"
  (println "children of:" name " " children)
;  (.log js/console children)
  (.createVNode  js/Inferno (get flags type) name (clj->js props) (clj->js children)))

(defn div [props & children]
  (println "div"  children)
  (builder :html-element "div" props children))

(defn p [props & children]
  (println "p"  children)
  (apply builder :html-element "p" props children))

(defn span [props & children]
  (println "span"  children)
  (apply builder :html-element "span" props children))

(defn h1 [props & [children]]
  (builder :html-element "h1" props children))

(defn textarea [props & children]
  (builder :textarea-element "textarea" props children))

(defn input [props & [children]]
  (builder :input-element "input" props children))

(defn select [props & [children]]
  (builder :select-element "select" props children))
