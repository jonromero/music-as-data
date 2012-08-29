(defproject music-as-data "0.7.0"
  :description "A Live Programming Language in Clojure"
  :url "http://mad.emotionull.com"
  :main music-as-data.core
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [overtone "0.7.1"]]
  :jvm-opts ["-Xms256m" "-Xmx1g" "-XX:+UseConcMarkSweepGC"])
