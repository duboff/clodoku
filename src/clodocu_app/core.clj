(ns clodocu-app.core
  (:gen-class))

(def field [
   0 0 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0
   0 1 0 0 0 0 0 0 0
   0 0 0 0 0 0 0 0 0
   0 0 0 4 0 0 0 0 0
   0 0 0 0 0 0 0 0 0
   0 0 0 0 6 0 0 0 0
   0 6 0 0 0 0 0 0 0
   0 1 0 0 0 0 0 0 0
  ])

(defn no_duplicates? [x]
  (def nozeroes (vec (remove #{0} x)))
  (def uniq (vec (set nozeroes)))
  (= (count uniq) (count nozeroes))
  )

(defn generate_groups []
  (concat 
   (for [x (range 9)] (filter #( = (mod % 9) x ) (range 81)))
   (for [x (range 9)] (filter #( = (quot % 9) x ) (range 81)))
   (for [x (range 9)] (filter #( = (+ (quot (mod % 9) 3) ( * (quot (quot % 9) 3) 3)) x ) (range 81))))
)

(defn row [indices] 
  (map #(nth field %) indices)
  )

(def groups
    (map row (generate_groups))
  )

(defn validate-field [field]
  (every? no_duplicates? field)
)

(defn -main [& args]
   (println (validate-field groups))) 


