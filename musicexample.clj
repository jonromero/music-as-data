;; (play (pattern [A B C [D E]]))
;; (play (bmp 10 (pattern [A B C [D E]])))
;; (play (+ 10 (pattern [A B C [D E]])))
;; (play (reverse (pattern [A B C [D E]])))
;; (play (pattern [sin])))
;; (play (pattern [kick crush kick crush])))
;; (play (pattern [(+ kick crush) boot (+ kick crush)]))
;; (def sigs (play (pattern [A B])))
;; (stop sigs)

;; play should execute the timings

(def kick (.loadSample @*minim* "/home/darksun4/Sources/clj-processing/examples/data/KickDrums1/kickdrum6.wav"))

(def snare (.loadSample @*minim* "/home/darksun4/Sources/clj-processing/examples/data/SnareDrums1/snaredrum2.wav"))

(def hihat (.loadSample @*minim* "/home/darksun4/Sources/clj-processing/examples/data/HiHats1/hihat2.wav"))

(defmacro times [num expression]
  ;; small tempo drums demo
  `(doseq [i# (range ~num)]
	~expression))

;; Drum Demos
(times 2 
  (p (pattern [kick (+ hihat snare)],2)))

(times 4
	   (p (pattern [kick (+ hihat snare) [kick kick] (+ hihat snare)],3)))

(times 4
	   (p (pattern [kick (+ hihat snare) [kick kick] (+ hihat snare)],3)))


(ns examples.music
  (:import [ddf.minim Minim AudioOutput AudioSample]
		   [ddf.minim.signals SquareWave]
		   [ddf.minim.effects LowPassFS])
  (:import (processing.core PApplet PImage PGraphics PFont))
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def *minim* (atom nil))
(def *outp* (atom nil))
(def *sine* (atom nil))
(def *samples-path* "/home/darksun4/Sources/clj-processing/examples/data/")

(defn play-note [note]
  (.playNote @*outp* (float 0)
			 (float (:duration note))
			 (:data note)))


(defn play-sample [sample]
  (.trigger (:data sample)))


(defrecord Melement [volume pitch duration play-fn data])

(defmacro defsample [sample sample-path]
  `(def ~sample
		(Melement. 50 (float 1.0) (float 1.0) play-sample
				   (.loadSample @*minim* (str *samples-path* '~sample-path)))))

(defmacro defnote [n-name]
  `(def ~n-name (Melement. 50 (float 1.0) (float 1.0) play-note (str '~n-name))))


(defn- play-chord
  "Executes the play-funcs of of all elements"
  [chord-elements]
  (doseq [element (:data chord-elements)]
	(play-element element)))


(defn join [& notes]
  (Melement. 0 0 0 true play-chord
			 (first (conj [] notes))))

(defn play-element [element]
  ((:play-fn element) element))



(defmacro + [& more]
  ;; make chords like (+ kick hihat)
  `(join ~@more))


;; Notes
(defnote A)
(defnote B)
(defnote C)
(defnote D)
(defnote E)
(defnote F)
(defnote G)
(defnote E3)
(defnote G3)
(defnote B3)
(defnote E4)
(defnote _)
(def _ (Melement. 50 1.0 nil (fn[x] nil) "_"))

;; Drums
(defsample kick "KickDrums1/kickdrum6.wav")
(defsample snare "SnareDrums1/snaredrum2.wav")
(defsample hihat "HiHats1/hihat2.wav")

(def a (.loadSample @*minim* "/home/darksun4/Sources/clj-processing/examples/data/BD.mp3"))
(.trigger a)
(.close a)

;; Nothing else matters
(p (pattern [E3 G3 B3 E4 B3 G3], 4))

;; Drum demo
(p (pattern [kick kick hihat [kick kick] [kick kick] [kick kick] hihat], 4))

;; Working example
;; (p (pattern [kick (+ hihat snare) kick],2))
(defn show-properties [prop pattern]
  (map #(prop %)
	   pattern))


(defn calc-duration [elements duration count]
  (map #(pattern % (/ duration count))
	   elements))


;; Maybe a reduce could clean it up more?
(defn pattern
  ([m-element] (pattern m-element 1))
  ([m-element duration]
	 (if (= (type []) (type m-element))
	   (flatten
		(calc-duration m-element duration (count m-element)))
		(assoc m-element :duration duration))))


(defn p
  [elements]
  (doseq [element elements]
	(play-element element)
	(Thread/sleep (* (:duration element) 500))))


(defn bpm [pattern tempo-num]
  (map #(+ (:duration %)
		   tempo-num)
	   pattern))

(defn setup []
  "Runs once."
  (swap! *minim* (fn [minim] (Minim. *applet*)))
  (swap! *outp* (fn [out]
				  (.getLineOut @*minim*)))
  (swap! *sine* (fn [sine]
				  (SquareWave. 440 0.5 (.sampleRate @*outp*)))))



(defn draw []
  (background-float 124))

(defapplet example :title "Music as Data"
  :setup setup :draw draw :size [200 200])

(run example)
(stop example)


  float pan = map(mouseX, 0, width, -1, 1);
  sine.setPan(pan);


(.addSignal @*outp* @*sine*)
(.removeSignal @*outp* @*sine*)
(.setFreq @*sine* 40)

(doseq [i (take 400 (repeatedly (fn [] (rand 400))))]
  (.setFreq @*sine* i)
  (Thread/sleep 100))

(def sqr
	 (SquareWave. 440 0.5 44100))

(.removeSignal @*outp* @*sine*)
 
(.clearSignals @*outp*)

(.playNote @*outp* (float 0) (float 1.0) "A"))

(.close @*outp*)


(defn print-progress-bar [percent]
  (let [bar (StringBuilder. "[")] 
    (doseq [i (range 50)]
      (cond (< i (int (/ percent 2))) (.append bar "=")
            (= i (int (/ percent 2))) (.append bar ">")
            :else (.append bar " ")))
    (.append bar (str "] " percent "%     "))
    (print "\r" (.toString bar))
    (flush)))

