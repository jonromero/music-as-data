;; Jon Vlachoyiannis 07/03/2011
;; jon@emotionull.com
(ns music-as-data.core
  (:import [ddf.minim Minim]
		   [ddf.minim.signals SquareWave]
		   [ddf.minim AudioInput]
		   [ddf.minim AudioOutput]
		   [ddf.minim.signals SineWave])
  (:use [quil.core]
        [quil.applet]
        [quil.dynamics])
  (:use [music-as-data.mad]
		[music-as-data.elements]
		[music-as-data.globals]
		[music-as-data.record]
                [music-as-data.semantics]
		[music-as-data.signalsnotes]))

(defn -main []
  (create-notes)
  (p (pattern [A4 A2]))
)

(defn setup []
  "Runs once."
  (swap! *minim* (fn [minim] (Minim. (current-applet))))
  (swap! *outp* (fn [out]
				  (.getLineOut @*minim*)))
  (swap! *sine* (fn [out]
				  (new SineWave 440 1 (.sampleRate @*outp*)))))

(defn draw []
  (background-float 0)
  (stroke 255))

(defapplet main :title "Music as Data"
  :setup setup :draw draw :size [200 200])

(defn start [applet]
  (applet-start applet))

(defn end [applet]
  (applet-exit applet))


;; Run this AFTER you have started the system
;; using
;;(start main)

;; Create notes and load samples
;; after system started

(create-notes)

(defsample kick "KickDrums1/kickdrum6.wav")
;;(defsample kick2 "KickDrums4/kickdrum154.wav")
(defsample snare "SnareDrums1/snaredrum2.wav")
;;(defsample snare2 "DistortedSnares2/distortedsnare52.wav")
(defsample hihat "HiHats1/hihat2.wav")

;(p (pattern [A4]))

;;(play! [A4 B4])
;;(keep-looping)
