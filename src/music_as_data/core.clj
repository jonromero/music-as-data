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


(definst dubstep [freq 100 wobble-freq 2 dur 0.4]
  (let [sweep (lin-exp (lf-saw (+ (line 1 40 5) wobble-freq)) -1 1 10 5000)
        env (env-gen (perc 0.01 dur) :action FREE)
        son   (mix (saw (* freq [0.99 1 1.01])))]
    (* env (lpf son sweep))))

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

(definst wobblez [pitch-freq 440 wobble-freq 5 wobble-depth 100 dur 0.4]
  (let [wobbler (* wobble-depth (sin-osc wobble-freq))
        env (env-gen (perc 0.01 dur) :action FREE)
        freq (+ pitch-freq wobbler)]
    (* env (sin-osc freq))))

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


(definst dubkick [freq 100 wobble-freq 2 dur 0.4]
  (let [sweep (lin-exp (lf-saw wobble-freq) -1 1 40 2000)
        env (env-gen (perc 0.01 dur) :action FREE)
        son   (mix (saw (* freq [0.99 1 1.01])))]
    (* env (lpf son sweep))))


(definst drums-doup [freq 100 wobble-freq 2 dur 0.4]
  (let [sweep (lin-exp (lf-saw wobble-freq) -1 1 30 2000)
        env (env-gen (perc 0.01 dur) :action FREE)
        son   (mix (saw (* freq [0.99 1 1.01])))]
    (* env (lpf son sweep))))

(definst drums-pap [freq 100 wobble-freq 2 dur 0.4]
  (let [sweep (lin-exp (lf-saw wobble-freq) -1 1 40 2000)
        env (env-gen (perc 0.01 dur) :action FREE)
        son   (mix (saw (* freq  [0.99 1 1.01])))]
    (* env (lpf son sweep))))

(sword "doup" drums-doup 20 110 4)
(sword "pap" drums-pap 200 210 4)
(sword "_" drums-pap 10 0 4)
(sword "wob" dubstep 440 10 10)

(inst-fx! drums-doup fx-distortion2)
(inst-fx! drums-pap fx-distortion2)

(clear-fx drums-doup)
(clear-fx drums-pap)

(p (cycle (pattern [doup pap] 1)))
(demo (bpf (play-buf 1 flute-buf (line 0.1 0.3 5)) (line 1 1600)))

(p (pattern [wob (assoc wob :pitch 1600)] 2))

(inst-fx! tone fx-reverb)
(inst-fx! tone fx-distortion2)
(clear-fx tone)

(defn dub-note [n]
  {:synth dubstep
   :vol 10
   :pitch (midi->hz (note n))
   :dur 4})

(p (cycle (pattern [(dub-note :a3)
                    (dub-note :b4)
                    (dub-note :b#4)
                    (dub-note :b4)] 10)))

(stop)


(defn sword [n-sym your-inst pitch vol dur]
  (intern *ns* (symbol n-sym)
          {:synth your-inst
           :vol vol
           :pitch pitch
           :dur dur}))


(dubstep (midi->hz (note :a3)))
(dubstep (midi->hz (note :b4)))
(dubstep (midi->hz (note :b#4)))
(dubstep (midi->hz (note :b4)))

;;http://soundcloud.com/skipcloud/157-miles



(sword "the-kick" tone 440 50 0)
(sword "the-kick3" kick 1 10 0)

(sword "wob" dubkick 10 210 10)
(p (pattern [wob]))

(p (pattern [(assoc wob :pitch 500)]))
