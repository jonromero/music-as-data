;; Jon Vlachoyiannis 07/03/2011
;; jon@emotionull.com

(ns music-as-data.mad
  (:use [overtone.live]
        [overtone.inst.synth]))

(definst tone [note 60 amp 0.3 dur 0.4]
  (let [snd (sin-osc (midicps note))
        env (env-gen (perc 0.01 dur) :action FREE)]
    (* env snd amp)))

(defn p
  ([elements]
   (p elements (now)))
  ([[{:keys [synth vol pitch dur data]} & elements] t]
   (let [next-t (+ t (int (* 1000 dur)))]
     (at t
         (synth pitch vol dur))
     (when elements
       (apply-at next-t #'p elements [next-t])))))

(declare calc-duration)

(defn pattern
  ([m-element] (pattern m-element 1))
  ([m-element duration]
   (if (= (type []) (type m-element))
     (flatten
       (calc-duration m-element duration (count m-element)))
     (assoc m-element :dur (float duration)))))

(defn calc-duration
  [elements duration count]
  (map #(pattern % (/ duration count))
       elements))

(defn defnote
  [n-sym pitch]
  (intern *ns* n-sym
          {:synth tone
           :vol 0.2
           :pitch pitch
           :dur 0.1
           :data []}))

(defn def-notes
  "Define vars for all notes."
  []
  (doseq [octave (range 8)]
    (doseq [n (range 7)]
      (let [n-char (char (+ 65 n))
            n-sym (symbol (str n-char octave))
            note (octave-note octave (get NOTES (keyword (str n-char))))]
        (defnote n-sym note)
        (when-let [sharp (get NOTES (keyword (str n-char "#")))]
          (defnote (symbol (str n-char "#" octave))
                   (octave-note octave sharp)))
        (when-let [flat (get NOTES (keyword (str n-char "b")))]
          (defnote (symbol (str n-char "b" octave))
                   (octave-note octave flat)))))))
