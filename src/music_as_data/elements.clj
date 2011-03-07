(ns music-as-data.elements
  (:use [music-as-data.mfunctions]
		[music-as-data.globals]))

(defrecord Melement [volume pitch duration play-fn data])

(def _ (Melement. 50 1.0 nil (fn[x] nil) "_"))

(defmacro defsample [sample sample-path]
  `(def ~sample
		(Melement. 50 (float 1.0) (float 1.0) play-sample
				   (.loadSample @*minim* (str (get-samples-path) '~sample-path)))))

(defmacro defmidi-note [n-name]
  `(def ~n-name (Melement. 50 (float 1.0) (float 1.0) play-note (str '~n-name))))


