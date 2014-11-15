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
  (require 'music-as-data.core)
  (in-ns 'music-as-data.core)

  (defsample hihat "HiHats1/hihat2.wav")

  (create-notes)
  (p (pattern [(chord A2 E5)]))
  (p [(chord hihat A3)])

  (defn melody []
    (p [C4])
    (p [G3])
    (p [A3])
    (p [E3])
    (p [F3])
    (p [C3])
    (p [F3])
    (p [G3])
  )
  ;; (times 4 (p (pattern [C4] [_] [G3] [_] [A3] [_] [E3] [_] [F3] [_] [C3] [_] [F3] [_] [G3])))
  (times 1 (melody))

  (defn fast_part []
    (p [C4 C4])
    (p [D4 B3])
    (p [C4 E4])
    (p [G4 G3])
    (p [A3 F3])
    (p [E3 G3])
    (p [F3 C4])
    (p [B3 G3])
    (p [C4])
  )

  (times 2 (fast_part))

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
