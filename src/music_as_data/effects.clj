;; FIXME
(defn trancento [how-much notes]
  (map #(assoc % :pitch
			   (clojure.core/+ (:pitch %)
							   how-much))
	   notes))

