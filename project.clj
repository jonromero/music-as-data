(defproject music-as-data "0.6.0"
  :description "A Live Programming Language in Clojure using processing"
  :url "http://mad.emotionull.com"
  :main music-as-data.core
  :dependencies [[org.clojure/clojure "1.2.0"]
				 [org.clojars.automata/rosado.processing "1.1.0"]
				 [org.clojars.automata/ddf.minim "2.1.0"]				 
				 [org.clojars.automata/minim-spi "2.1.0"]]
  :dev-dependencies [[swank-clojure "1.2.0"]]
  :jvm-opts ["-Xms256m" "-Xmx1g" "-XX:+UseConcMarkSweepGC" "-server"])

