(ns paradiso.dom)

(defn tag-definition
  [fname]
  (let [f-fname (symbol fname)]
     `(defn ~f-fname ~'[props & children]
        (js/Inferno.createVNode 2 ~fname ~'(clj->js props) ~'(clj->js children) nil nil nil))))

(defn svg-tag-definition
  [fname]
  (let [f-fname (symbol fname)]
     `(defn ~f-fname ~'[props & children]
        (js/Inferno.createVNode 128 ~fname ~'(clj->js props) ~'(clj->js children) nil nil nil))))

(defmacro define-svg-tags
  [& tags]
  `(do (do ~@(->> (clojure.core/map str tags)
                  (clojure.core/map svg-tag-definition)))))

(defmacro define-tags
  [& tags]
  `(do (do ~@(->> (clojure.core/map str tags)
                  (clojure.core/map tag-definition)))))
      ;  (def ~'defined-tags
      ;    ~(zipmap (map (comp keyword name) tags)
      ;             tags))))
