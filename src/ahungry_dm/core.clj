(ns ahungry-dm.core
  (:require
   [ahungry-dm.gui :as g])
  (:gen-class))

(defn main [& args]
  (println "Hello, World!")
  (g/main))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (g/main-with-exit))
