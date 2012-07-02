(ns music-as-data.globals)

(def ^:dynamic *samples-path* "samples/")

(defn get-samples-path []
  *samples-path*)

(def  ^:dynamic *minim* (atom nil))
(def  ^:dynamic *outp* (atom nil))
(def  ^:dynamic *sine* (atom nil))
(def  ^:dynamic *pattern* (atom []))
(def  ^:dynamic *recorder* (atom []))

