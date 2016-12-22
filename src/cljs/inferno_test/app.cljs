(ns inferno-test.app
  (:require [cljs.inferno :as i]
            [inferno-test.dom :as d]))

(enable-console-print!)


(defn render [element node]
  "Given an Element, immediately render it, rooted to the
   specified DOM node."
  (.render js/Inferno element node))

(defn page []
  (d/div {}
    (d/h1 {} "Hello")
    (d/input {:placeholder "Write here"})
    (d/h1 {} "There")
    (d/p {}
      (d/span {} "I want to say something."))))


(defn init []
  (let [node (. js/document (getElementById "container"))
        v-node (page)]
    (render v-node node)))
