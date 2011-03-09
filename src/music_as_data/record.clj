(ns music-as-data.record
  (:use [music-as-data.mad]
		[music-as-data.elements]
		[music-as-data.globals]))

(defn start-rec [filename]
  (swap! *recorder* (fn [out] (.createRecorder @*minim* @*outp* (str filename ".wav") true)))
  (.beginRecord @*recorder*))

(defn stop-rec []
  (.endRecord @*recorder*)
  (.save @*recorder*))

  



