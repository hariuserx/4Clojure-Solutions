(fn [s] (= (count (reduce (fn [res x] (clojure.set/union res x)) #{} s))
           (apply + (map count s))))