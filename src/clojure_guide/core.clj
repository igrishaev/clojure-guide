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


(def storage
  (atom {}))

(defn set-item [idx item]
  (swap! storage assoc idx item))

(defn get-item [idx]
  (get @storage idx))


(def *storage
  (atom {}))


(def atom-storage (atom {}))
(def a-storage (atom {}))

(def storage! (atom {}))

(swap! storage! ...)
(reset! storage! ...)


(let [rows! (transient [])]
  (doseq ...
    (conj! rows! item))
  (persistent! rows!))


(defn get-user [user-id]
  (try
    (jdbc/get-by-id *db* :users user-id)
    (catch Throwable e
      e)))



(let [user (get-user 1)]
  (if (throwable? user)
    (do
      (log/error ...)
      (throw user))
    (process user ...)))


(defn get-user [user-id]
  (try
    (jdbc/get-by-id *db* :users user-id)
    (catch Throwable e
      {:type :error :message (ex-message e)})))


(let [user (get-user 1)]
  (if (error-map? user)
    (do
      (log/error ...)
      (throw ...))
    (process user ...)))


(throw (ex-info "Some message" {:some "context"}))



(defn error!

  ([message]
   (throw (ex-info message {})))

  ([message data]
   (throw (ex-info message data)))

  ([message data cause]
   (throw (ex-info message data cause))))


(when-not user
  (e/error! "User not found" {:id user-id}))


(set! *assert* false)

(let [data {:foo 1}]
  (assert (get data :bar) "Bar is missing"))

(set! *assert* false)

(set! *assert* true)

(defn process-user2
  [user]
  {:pre [(some? user)]}
  (assoc user :some "field"))

(process-user2 nil)


(filter #(= (:id %) product-id) products)
(update :body #(json/parse-string % ->kebab-case-keyword))


(filter (fn [product]
          (= (:id product) product-id))
        products)

(update :body json/parse-string ->kebab-case-keyword)



(map inc numbers)

(map str objects)



(map (fn [{:keys [...]}]
       (if this
         (with-something
           (log/info ...)
           (do-that this))
         (let [foo 1
               bar 2]
           (therwise this ...)))) entities)



(defn process-entity [entity]
  ...)

(map process-entity entities)


(for [entity entities]
  (let [{:keys [...]}]
    (return something)))



(->> items
     (map (partial process-item context current-time))
     (filter (partial check-item event-type)))



(def enumerate
  (partial map-indexed vector))

(enumerate [:a :b :c])

([0 :a] [1 :b] [2 :c])


(let [fn-process
      (comp str inc abs)]
  (fn-process -3))


((comp str inc abs) -3)


(map (comp this that more) [...])


(defn make-xform [...]
  (comp (map ...)
        (filter ...)))


(let [xform (make-xform)]
  ...)


(defn connect [host port & {:keys [log-level
                                   log-file]}]
  ...)

(connect "localhost" 5432 :log-level "info" :log-file "log.txt")


(defn connect
  ([host port]
   (connect host port nil))

  ([host port {:keys [log-level log-file]}]
   ...))


(let [options {...}]
  (connect host port options))


(def response
  {:status 200
   :headers {,,,}
   :body {:user {:email "test@test.com"
                 :full-name "Ivan"}}})

(let [{:keys [status body]}
      response

      {:keys [user]}
      body

      {:keys [email full-name]}
      user]

  (println email full-name))


(let [{{{:keys [email full-name]} :user} :body}
      response]
  (println email full-name))


(let [{:keys [:status :body]}
      response]
  ,,,)


(def data
  {:user/name "Ivan"
   :event/name "Party"})


(require 'cheshire.core)

(cheshire.core/generate-string data)

(let [{:user/keys [name]
       :event/keys [name]} data]
  (println name))
;; Party


(let [{user-name :user/name
       event-name :event/name} data]
  (println user-name "@" event-name))


(let [user
      {:user/name "..."
       :user/email "..."
       :user/dob "1985-..."
       :user/active? true}]
  ,,,)

(let [user
      {:name "..."
       :email "..."
       :dob "1985-..."
       :active? true}]
  ,,,)


(let [row
      {:user/name "..."
       :user/email "..."
       :user/dob "1985-..."
       :user/active? true
       :profile/user-id 1
       :profile/created-at "..."
       :profile/avatar "..."}]
  ,,,)


(let [row
      {:user-name "..."
       :user-email "..."
       :user-dob "1985-..."
       :user-active? true
       :profile-user-id 1
       :profile-created-at "..."
       :profile-avatar "..."}]
  ,,,)

(let [row
      {:user {:name "..."
              :email "..."
              :dob "1985-..."
              :active? true}
       :profile {:user-id 1
                 :created-at "..."
                 :avatar "..."}}]
  ,,,)


(require '[clojure.walk :as walk])

(walk/postwalk
 (fn [x]
   (if (map?)
     (update-keys x csk/->SCREAMING_SNAKE_CASE)
     x))
 data)



(let [{:keys [user_id
              user_name]} db-row]
  (println user_id user_name))


(let [{:keys [ResponseError
              ResponseMessage]}
      api-response

      {:keys [Code Category]}
      ResponseError]

  (println Code Category ResponseMessage))





(get-in body [:transactionInfo :userName])

{:transaction-info
 {:user-name {...}}}



{:global-vars {*warn-on-reflection* true
               *assert* true
               *print-meta* false}}


{:user
 {:global-vars {*warn-on-reflection* true
                *assert* true
                *print-meta* false}}}


;; lein uberjar > uberjar.log
;; ! grep -i 'Reflection warning' uberjar.log



(defn upsert-user! [db fields]
  ...)


(defn create-user [db fields]
  ...)

(defn delete-user [db fields]
  ...)

(defn update-user [db fields]
  ...)


(defn get-last-task [db client-id]
  ...)


(defn orders->total [orders]
  ...)


(defn datetime->date [dt]
  ...)


(defn orders+clients->total [orders clients]
  ...)


(defn is-active? [user]
  ...)


(let [active? (is-active? user)]
  ...)

(let [?active (is-active? user)]
  ...)


(defn process-stats [data]
  ...)


(defn process-stats [orders users]
  ...)

(process-stats [{:order-id 1 ...} {:order-id 2 ...}]
               [{:user-id 10 ...} {:user-id 20 ...}])



(defn process-users [id->user]
  ...)

(process-users {1 {:user-id 1 ...},
                2 {:user-id 2 ...}})


(def verb->path->response
  {:get {"/" {:status 200 :body ...}
         "/help" {:status 200 :body ...}}
   :post {"/users" {:status 400 :body ...}}})


(with-http-server verb->path->response
  ...)


(defn error-handler [e]
  ...)

(connect db {:fn-on-error error-handler})


(defn process-events [vec-events int-limit str-notice]
  ...)


(defn process-user [u p]
  ...)

(defn process-user [user profile]
  ...)

(def user {:name "Ivan"})

(let [{:keys [name]} user]
  (println name))

(let [{:keys [full-name]} user]
  (println name))
;; #function[clojure.core/name]


(let [{user-name :name} user]
  (println name))


(defn some-function [a b]
  (with-some-macro {:foo 42}
    (let [x (+ a b)]
      (dotimes [_ 99]
        (println "hello")))))


(calling-a-func-with-args "arg one"
  {:some {:nested "map"}}
  some-variable
  [1 2 3]
  true)


(defn some-long-function [a b c d]
  (let [calc-this
        (some-vast-function a c)

        calc-that
        (some-another-function c d)

        users
        (some-massive-query-to-db calc-that)

        events
        (some-http-request ...)]
                                  ;; <- !!!
    (for [user users]
      ...)))


(defn get-by-id
  ([table id]
   (get-by-id table id :id))

  ([table id pk-name]
   (jdbc/execute ...)))




(defn error!

  ([message]
   (throw (ex-info message {})))

  ([message data]
   (throw (ex-info message data)))

  ([message data cause]
   (throw (ex-info message data cause))))


(defn error!
  ([message]
   (throw (ex-info message {})))
  ([message data]
   (throw (ex-info message data)))
  ([message data cause]
   (throw (ex-info message data cause))))




(defn some-complex-func
  "This function does this and that..."
  [users limit some-arg]
  {:pre [(seq users) (int? limit)]
   :post [(map? %)]}
                     ;; <- !!!
  (let [events
        (get-events ...)]
    ...))



(doseq [item items
        :let [{:keys [id title]} item]
        :when (some? id)]
                           ;; <- !!!
  (process-item ...))









#_ _
