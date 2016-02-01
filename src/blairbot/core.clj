(ns blairbot.core)
(use '[clojure.string :only (split lower-case)])
;;Core data
(def eng-greetings #{"hello" "good morning" "hi" "yo", "whats up"})
(def eng-valedictions #{"bye", "goodbye", "good bye", "see ya", "peace out", "see ya later", "see you later"})
(def eng-interrogative #{"what" "which" "whose" "who" "when" "why"})
(def eng-commands #{"open" "close"})

(defn normalize-word [a] (lower-case (clojure.string/replace a #"[^A-Za-z0-9 ]" ""))) 
(defn first-word [[a]] (lower-case (str a)))
(defn first-two-words [[a b]] (lower-case (str a " " b)))
(defn first-three-words [[a b c]] (lower-case (str a " " b " " c)))
(defn is-greeting [words]
  (if (instance? String words)
    (contains? eng-greetings  (lower-case words))
    (or (contains? eng-greetings (first-word  words))
        (contains? eng-greetings (first-two-words  words)))
    )
  )
(defn is-question [words]
  (if (instance? String words)
    (contains? eng-interrogative (lower-case words))
    (contains? eng-interrogative (first-word words)))
  )
(defn is-command [words]
  (if (instance? String words)
    (contains? eng-commands (lower-case words))
    (contains? eng-commands (first-word words)))
  )
(defn is-valediction [words]
  (if (instance? String words)
    (contains? eng-valedictions (lower-case words))
    (or (contains? eng-valedictions (first-word  words))
        (contains? eng-valedictions (first-two-words  words))
        (contains? eng-valedictions (first-three-words  words))))
  )

(defn run-command [command]
  (println (str command "  not implemented."))
)

(defn comprehend [words]
  (cond (is-greeting words) "hello"
        (is-valediction words) "good-bye"
        (is-command words) (run-command words)
        (is-question words) (println "Thanks for asking.")
        :else "Wie bitte?"
        ))
(defn listen [said]
  (let [response (comprehend (split said #" "))]
    (if response
      (println response))
    response
    )
)

(defn random-line [resource-file]
  (def file (clojure.java.io/file (clojure.java.io/resource resource-file)))
  (rand-nth (split (slurp file) #"\n"))
  )
