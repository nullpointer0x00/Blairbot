(ns blairbot.bot
  (:require [blairbot.core :refer :all]
            [blairbot.google.translate :refer :all]))

(defn wakeup-sequence
  "Things to do when starting Blair up."
  []
  (println "Waking up.")
  (let [word (random-line "words/top10000de.txt")]
    (println (str "The german word '"  word "' means '" (translate-de word) "' in english." ))
    )
  )

(defn -main
  []
  (wakeup-sequence)
  (while
      (= true true)
    (print "> ")
    (listen (str (read-line)))
    )
  )
