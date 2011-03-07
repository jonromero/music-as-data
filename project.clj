(defproject org.clojars.automata/rosado.processing "1.1.0"
  :description "A Live Programming Music REPL using processing"
  :url "http://mad.emotionull.com"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojars.fyuryu/processing.core "1.1.0"]]
  :dev-dependencies [[lein-clojars "0.5.0-SNAPSHOT"]
					 [swank-clojure "1.2.0"]]
  :jvm-opts ["-Xms256m" "-Xmx1g" "-XX:+UseConcMarkSweepGC" "-server"])

