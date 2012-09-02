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



(demo (wobble2 440 1 10))
(stop)


(definst dubstep [freq 100 wobble-freq 2 bla 0]
  (let [sweep (lin-exp (lf-saw wobble-freq) -1 1 40 5000)
        son   (mix (saw (* freq [0.99 1 1.01])))]
    (lpf son sweep)))

(dubstep (midi->hz (note :a4)))
(ctl dubstep :freq (midi->hz (note :b4)))

(def kick (sample (freesound-path 2086))) ;; downloading sounds //wicked!
(kick)

(stop)


(defn defsample2 [n-sym path]
  (intern *ns* n-sym
          {:synth wobble2
           :vol 0.2
           :pitch pitch
           :dur 0.1}))


(defsample2 iknow "/Users/jonromero/Sources/beat-me-up-scotty/samples/safe_tribble.wav")

(definst wobblez [pitch-freq 440 wobble-freq 5 wobble-depth 100]
  (let [wobbler (* wobble-depth (sin-osc wobble-freq))
        freq (+ pitch-freq wobbler)]
    (sin-osc freq)))

(wobble2)

(intern *ns* (symbol "helloz2")
        {:synth kick
         :vol 1.0
         :pitch 1
         :dur 0.1})


;; first lets load a sound file
;; create a M.A.D specificication is super easy.
;; All we need is a synth to feed to overtone
;; this new structure to be added to the namespace

(defn sword [n-sym your-inst pitch vol dur]
  (intern *ns* (symbol n-sym)
          {:synth your-inst
           :vol vol
           :pitch pitch
           :dur dur}))


(sword "the-kick" kick 1 1 1)
(sword "the-kick2" kick 1 1 1)
(sword "wob" dubstep 440 10 10)

(p (pattern [(assoc wob :pitch 500)]))
