;; Jon Vlachoyiannis 07/03/2011
;; jon@emotionull.com
(ns music-as-data.core
  (:use [music-as-data.mad]))


(defn start
  "Starts M.A.D."
  []
  (def-notes))


(def derezzed [[E4 G4 E4] [E5 B4 G4 D4 A4 E4 G4 A4]])
(p (pattern derezzed 2))

(def flute-buf (load-sample "/Users/jonromero/Sources/beat-me-up-scotty/samples/safe_tribble.wav"))

(defsynth reverb-on-left []
  (let [dry (play-buf 1 flute-buf 0.3 )]
;;        wet (free-verb dry 1)]
    (out 0 [dry])))

(play-buf 1 flute-buf 0.3 )
(reverb-on-left)

(defsample not-such-thing "/Users/jonromero/Sources/beat-me-up-scotty/samples/safe_tribble.wav")

(not-such-thing)

(demo (hpf (play-buf 1 flute-buf 0.3) 770) 100)

(demo (bpf (play-buf 1 flute-buf (line 0.1 0.3 5)) (line 1 1600)))


(definst wobble2 [pitch-freq 440 wobble-freq 5 wobble-depth 5]
  (let [wobbler (* wobble-depth (sin-osc wobble-freq))
        freq (+ pitch-freq wobbler)]
    (sin-osc freq)))

(demo (wobble2 440 1 10))
(stop)


(definst dubstep [freq 100 wobble-freq 2]
  (let [sweep (lin-exp (lf-saw wobble-freq) -1 1 40 5000)
        son   (mix (saw (* freq [0.99 1 1.01])))]
    (lpf son sweep)))

(dubstep 440 2)
(ctl dubstep :wobble-freq 2)

(stop)
