(defmacro get-note-freq
  "Converts Ab4 to notes: A b 4 and return frequency"
  [snote]
  `(let [note# (map char (str '~snote))]
	 (calc-freq
	  (if (> (count note#) 2)
		(zipmap
		 '(:note :half :scale )
		 note#)
		(zipmap
		 '(:note :scale)
		 note#)))))

(defn calc-freq [sig-note]
  (*
   (clojure.core/+ 
	(cond (= \C (:note sig-note)) 16.35
		  (= \D (:note sig-note)) 18.35
		  (= \E (:note sig-note)) 20.60
		  (= \F (:note sig-note)) 21.83
		  (= \G (:note sig-note)) 24.50
		  (= \A (:note sig-note)) 27.50
		  (= \B (:note sig-note)) 30.87)
	(cond (not (contains? sig-note :half)) 0
		  (= \# (:half sig-note)) 1.0594
		  (= \b (:half sig-note)) -1.0594))
   (Math/pow 2 (Integer/parseInt (str (:scale sig-note))))))

(defn setFreq [signal freq]
  (assoc signal :pitch freq))


(defn play-signal [signal]
  (.setFreq (:data signal) (:pitch signal))  
  (.addSignal @*outp*
			  (:data signal))
  (Thread/sleep (* (:duration signal) 500))	
  (.removeSignal @*outp*
				 (:data signal)))

(defmacro defnote [note-name global-signal]
  `(def ~note-name
		(Melement. 50 (float (get-note-freq ~note-name)) (float 1) play-signal ~global-signal)))
