(ns blairbot.core-test
  (:require [clojure.test :refer :all]
            [blairbot.core :refer :all]
            [blairbot.google.translate :refer :all]))

(deftest normalize-word_test
  (is (= "abcdefghijklmnopqrstuvwxyz0123456789" (normalize-word "!@#$%^&*()-_=+{}[]|:;<>,./?abcdefghijklmnopqrstuvwxyz0123456789")))
  (is (= "abcdefghijklmnopqrstuvwxyz0123456789" (normalize-word "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")))
  (is (= "" (normalize-word "?!@#$%^")))
  (is (= "" (normalize-word "")))
  )
(deftest first-word_test
  (is (= "bugs" (first-word ["BUGS"]))) 
  (is (= "bugs" (first-word ["bugs" "Help"]))))
(deftest first-two-words_testy
  (is (= "hello boggs" (first-two-words ["heLLo" "BOGGS"])))
  (is (= "soft butter" (first-two-words ["soft" "butter" "stocks"]))))
(deftest greeting_test
  (is (= true (is-greeting "Hello")))
  (is (= false (is-greeting "bu")))
  (is (= true (is-greeting "HI")))
  (is (= true (is-greeting ["good" "morning"])))
  (is (= true (is-greeting ["good" "MORNING"])))
  (is (= false (is-greeting ["good" "m" "morning"])))
  (is (= true (is-greeting ["hello" "m" "morning"]))))
(deftest is_valediction_test
  (is (= true (is-valediction "bye")))
  (is (= false (is-valediction "by")))
  (is (= true (is-valediction ["ByE"])))
  (is (= true (is-valediction ["bye" "sorry"])))
  (is (= true (is-valediction ["good" "BYE"])))
  (is (= true (is-valediction ["GOODBYE"])))
  (is (= true (is-valediction "see yA")))
  (is (= true (is-valediction ["peace" "out"])))
  (is (= true (is-valediction ["see" "ya" "later"])))
  (is (= true (is-valediction ["see" "you" "later"])))
  (is (= false (is-valediction ["see" "bbdfrs" "later"])))
  (is (= false (is-valediction [""])))
  (is (= false (is-valediction "")))
  )
(deftest is_command_test
  (is (= true (is-command ["open" "door"])))
  (is (= true (is-command  "open")))
  (is (= false (is-command  "dopen")))
  (is (= true (is-command  ["open"])))
  (is (= true (is-command  ["OpEn"])))
  (is (= true (is-command  ["close"])))
  (is (= false (is-command  ["dopen"]))))
(deftest is_question_test
  (is (= true (is-question ["who" "open" "door"])))
  (is (= false (is-question  "w")))
  (is (= true (is-question  "who")))
  (is (= true (is-question  ["who"])))
  (is (= true (is-question  ["WHAT" "" ""])))
  (is (= true (is-question  ["Which"])))
  (is (= true (is-question  ["WHoSe"])))
  (is (= true (is-question  ["whEn"])))
  (is (= true (is-question  ["Why"])))
  (is (= false (is-question  ["wwhat"]))))
(deftest listen_test
  (is (= "hello" (listen "Hello friend")))
  (is (= nil (listen "open the door")))
  (is (= "Wie bitte?" (listen "nothing"))))
(deftest random_line_test
  (is (= false (clojure.string/blank? (random-line "words/top10000de.txt"))))
  )

(deftest google_translate_translate-de_test
  (is (= "go" (translate-de "gehen")))
  )
