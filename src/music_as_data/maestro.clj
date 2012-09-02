;; Jon Vlachoyiannis 01/09/2012
;; jon@emotionull.com

(ns music-as-data.maestro)



(defn maestro [])


(p (pattern [A1 A2])) ;; plays on phrase / returns a key


(p ;; parallel play
 (pattern [A1 A2])
 (pattern [A2 A3])
 (pattern [C1 C4])

 ;; time is created in p function.
 ;; that means that it can calculate when a specific phrase
 ;; should be played IF the whole phrase is available
 ;; it pre-calcs the timings

 ;; That means:
 ;; We have a global hash.
 ;; Every phrase resides in this hash with a specific key (so we can remove it or add effects)
 ;; An "add" function will add a new phrase to the array and ensure that will be play on the next time
 ;; same with "remove"
 ;;
 ;;
 ;; That way ANYONE that has access to the REPL (remote?) can start adding or removing phrases ;)

 ;; we should be able to replicate the DJ experience (adding/removing
 ;; phrases dynamically - and in sync


 ;; EXAMPLE:
 ;; let's see that in live env
 (p (cycle (pattern [kick kick]))) ;; starts the beat / id:1
 ;; or
 ;; (p-loop (pattern [kick kick]))

 (p (cycle (pattern [A4 B5]))) ;; joins the party / id :2
 (p (cycle (pattern [A6 B7]))) ;; joins the party also / id :3


 (p (pattern [hey_omg])) ;; played only once / id :4

 (change 1 smth) ;; changes pattern 1. Maybe the pattern? The pitch? The synth?

 (stop 1) ;; removes the cycle
