(ns paradiso.dom)



(defn tag-definition
  "Return a form to defining a wrapper function for a ReactJS tag
  component."
  [tag]
  `(let [ctor# (js/Inferno.createVNode 2 ~(name tag) (clj->js props) (clj->js children) nil nil nil)] 
     (defn ~tag [& args#]
       ~(str "Return a component for " (name tag))
       (apply ctor# args#))))


(defmacro define-tags
  "Macro which expands to a do block that defines top-level constructor functions
  for each supported HTML and SVG tag, using quiescent.dom/construct."
  [& tags]
  `(do (do ~@(clojure.core/map tag-definition tags))
       (def ~'defined-tags
         ~(zipmap (map (comp keyword name) tags)
                  tags))))
