;(fn [x y] (loop [n 0 result {}] (if (= n (count y)) result (recur (inc n) (merge result {(nth y n) x})))))
#(zipmap %2 (repeat %))