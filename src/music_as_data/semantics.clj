(defn join [& notes]
  (Melement. 0 0 0 play-chord
			 (first (conj [] notes))))

;; Maybe defmulti?
(defmacro + [& more]
  ;; make chords like (+ kick hihat)
  `(join ~@more))
