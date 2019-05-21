(ns ahungry-dm.gui
  (:require
   [cljfx.api :as fx]
   [ahungry-dm.auth :as auth])
  (:import
   [javafx.scene.input KeyCode KeyEvent])
  (:gen-class))

(def *state (atom {}))

(defn maybe-start-session [{:keys [username password]}]
  (prn *state)
  (prn "MSS: " username password)
  (if (auth/simple-auth username password)
    (auth/start-session)
    (swap! *state assoc-in [:feedback] "Login failure!")))

(defn event-handler [e]
  (prn "Got an event: " e)
  (case (:event/type e)
    ::username-changed
    (swap! *state assoc-in [:username] (:fx/event e))
    ::password-changed
    (swap! *state assoc-in [:password] (:fx/event e))
    ::submit
    (do
      (swap! *state assoc-in [:submitted] (:fx/event e))
      (maybe-start-session @*state))
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

(defn root [{:keys [feedback]}]
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
            {:fx/type :text-field
             :text feedback}
            {:fx/type :button
             :text "Login"
             :on-action {:event/type ::submit}}
            ]}}})

(def renderer
  (fx/create-renderer
   :middleware (fx/wrap-map-desc assoc :fx/type root)
   :opts {:fx.opt/map-event-handler event-handler}))

(defn main [& args]
  (fx/mount-renderer *state renderer))
