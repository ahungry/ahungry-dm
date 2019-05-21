(ns ahungry-dm.auth
  ;; (:require)
  (:import
   [net.sf.jpam Pam PamReturnValue PamException])
  (:gen-class))

;; http://jpam.sourceforge.net/documentation/configuration.html
(defn simple-auth [username password]
  (let [pam (new Pam)]
    (. pam (authenticateSuccessful username password))))

(defn auth [username password]
  (let [pam (new Pam)]
    (. pam (authenticate username password))))

(defn verbose-auth [username password]
  (-> (auth username password) .toString))

(defn my-exec [cmd]
  (. (Runtime/getRuntime) exec cmd))

(defn fake-start-session []
  (my-exec "xcalc"))

(defn real-start-session []
  (my-exec "/bin/bash --login ~/.xinitrc"))

(defn start-x-server []
  (my-exec "/usr/bin/X :1 vt01"))

(defn start-session []
  ;; TODO: Use the xsession - this is a nice debug though
  ;; (fake-start-session)
  (start-x-server)
  (Thread/sleep 1000)
  (real-start-session))
