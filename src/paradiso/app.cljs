(ns paradiso.app
  (:require
    [paradiso.core :as d]))

(enable-console-print!)

(defn render [element node]
  "Given an Element, immediately render it, rooted to the
   specified DOM node."
  (js/Inferno.render element node))


(defn prev-next [prev next]
  (js/console.log "prev" prev " next " next))

(defn show-clock [props]
    (let [time (get (js->clj props) "time")]
      (d/p {:className "clock"} "Counter: " time)))

(defn Clock [time]
  (d/component show-clock {:time time}
    {:onComponentWillUpdate prev-next
     :onComponentDidMount #(js/console.log "Mounted")}))


(defn page [{:keys [time count]} callback]
  (d/div {}
    (d/h1 {:className "title"} "Title")
    (d/input {:placeholder "Write here"})
    (d/button {:onClick callback} "Push Die Button")
    (d/p {} "Count:" count)
    (d/a {:href "#farthing"} "Link")
    (Clock time)
    (apply d/ul {}
      (for [num (take 10 (range 11))]
        (d/li {} (str "Number: " num))))))


(defn init []
  (let [node (js/document.getElementById "container")
        time-atom (atom 0)
        count (atom 0)
        increment #(swap! count inc)
        update-fn #(do
                    (swap! time-atom + 1)
                    (render (page {:time @time-atom :count @count} increment) node))]
    (js/setInterval update-fn 1000)))
