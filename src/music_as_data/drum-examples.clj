;; TRON

(play! [kick2 kick2 kick2 kick2 snare2 kick2 kick2 kick2] 2)

;; EXAMPLE
(times 4 (p (pattern [[E4 G4 E4] [E5 B4 G4 D4 A4 E4 G4 A4]], 2)))

(p (pattern [E4 E4 E4 E4 E4],2.5))

;; Drum Demos
(times 2
  (p (pattern [kick (+ hihat snare)],2)))
(times 4
	   (p (pattern [kick (+ hihat snare) [kick kick] (+ hihat snare)],3)))
(times 4
	   (p (pattern [kick (+ hihat snare) [kick kick kick kick] kick kick kick kick (+ hihat snare)],3)))
