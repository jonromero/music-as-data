;; FIXME
(defn trancento [how-much notes]
  (map #(assoc % :pitch
               (clojure.core/+ (:pitch %)
                               how-much))
       notes))


(inst-fx! tone fx-distortion2) ;; changes the instrument
;; tone is an instrument

(definst tone [note 60 amp 0.3 dur 0.4]
  (let [snd (sin-osc (midicps note))
        env (env-gen (perc 0.01 dur) :action FREE)]
    (* env snd amp)))
