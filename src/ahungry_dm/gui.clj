(ns ahungry-dm.gui
  (:require
   [cljfx.api :as fx])
  (:import
   [javafx.scene.input KeyCode KeyEvent]))

(def *state (atom {}))

(defn event-handler [e]
  (prn "Got an event: " e)
  (case (:event/type e)
    ::username-changed
    (swap! *state assoc-in [:username] (:fx/event e))
    ::password-changed
    (swap! *state assoc-in [:password] (:fx/event e))
    ))

(defn text-input [{:keys [text label event-type]}]
  {:fx/type :h-box
   :spacing 5 :padding 5
   :children
   [{:fx/type :label :text label}
    {:fx/type :text-field
     :style {:-fx-font-family "monospace"}
     :text text
     :on-text-changed {:event/type event-type}}]})

(defn root [{:keys [_]}]
  {:fx/type :stage
   :showing true
   :title "ahungry-dm"
   :width 300
   :height 100
   :scene
   {:fx/type :scene
    :root {:fx/type :v-box
           :alignment :center
           :children
           [
            {:fx/type text-input
             :text ""
             :label "Username"
             :event-type ::username-changed}
            {:fx/type text-input
             :text ""
             :label "Password"
             :event-type ::password-changed}
            ]}}})

(def renderer
  (fx/create-renderer
   :middleware (fx/wrap-map-desc assoc :fx/type root)
   :opts {:fx.opt/map-event-handler event-handler}))

(defn main [& args]
  (fx/mount-renderer *state renderer))
