(ns clojure-guide.core)

(time
 (doseq [a [1 2 3]]
   (let [b (* a a)]
     (println b)
     )
   )
 )

(time
 (doseq [a [1 2 3]]
   (let [b (* a a)]
     (println b))))





(def mapping {
   :name "Ivan"
   :email "test@test.com"
})




(def numbers [
  1,
  2,
  3
])



1


(ns some.ns
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(ns some.ns
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))


(ns some.ns
  (:require
   [clojure [edn :as edn]
            [walk :as walk]
            [string :as str]]))


(ns some.ns
  (:require
   [clojure.walk :as clojure.walk]
   [cool.utils.walk :as utils.walk]))


(ns some.ns
  (:require
   [clojure.string :as s]))

(s/starts-with? ...)

(ns some.ns
  (:require
   [clojure.string :as str]))

(str/starts-with? ...)

(ns some.ns
  (:use clojure.walk))


(ns project.core
  (:require
   [cheshire.core :as json]
   [clojure.java.io :as io]
   [clojure.java.jdbc :as jdbc]
   [clojure.string :as str]
   [honey.sql :as sql]
   [project.handlers :as handlers]
   [project.routes :as routes]
   [project.server :as server]))



(ns project.core
  (:require
   project.db.json ;; extends JDBC with JSON support
   [project.handlers :as handlers]
   [project.routes :as routes]))



(defn get-user-info [user-id]
  (def user (get-user user-id))
  (def profile-id (:profile-id user))
  (def profile (get-profile profile-id))
  {:user user
   :profile profile})


(defn get-user-info [user-id]
  (let [user (get-user user-id)
        profile-id (:profile-id user)
        profile (get-profile profile-id)]
    {:user user
     :profile profile}))


(let [-rules (read-json "resources/rules.json")]
  (defn get-rule [id]
    (get-in -rules [:system :rules id])))


(def ^:dynamic *locale* nil)

(binding [*locale* :ru]
  (translate :message/hello))


(with-locale :ru
  (translate :message/hello))




















#_ _
