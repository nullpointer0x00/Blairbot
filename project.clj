(defproject blairbot "0.1.0-SNAPSHOT"
  :description "Blairbot - yet to understood"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http-lite "0.3.0"]
                 [org.clojure/data.json "0.2.6"]]
  :profiles {:dev {:plugins [[cider/cider-nrepl "0.8.2"]]}}
  :main blairbot.bot)
