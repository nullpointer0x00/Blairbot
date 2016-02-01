(ns blairbot.google.translate
  (:require [clj-http.lite.client :as client]
            [clojure.data.json :as json :only [read-json]]))
(def google-translate-url "https://www.googleapis.com/language/translate/v2?source=de&target=en&q=")
(def google-api-key "") 

(defn find-translatedText [json]
  (let [data (:data (json/read-json json))]
    (:translatedText (first (:translations data))))
)

(defn translate-de [de-words]
  (let [response (client/get (str google-translate-url de-words "&key=" google-api-key))]      
    (if (= 200 (:status response))
      (find-translatedText (:body response))
      (println "error")
      )
    )
  )
