(ns clojure-guide.core)

(time
 (doseq [a [1 2 3]]
   (let [b (* a a)]
     (println b)
     )
   )
 )

(def mapping {
   :name "Ivan"
   :email "test@test.com"
})

(def numbers [
  1,
  2,
  3
])



















#_ _
