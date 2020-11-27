(fn [s x]
  (loop [res (vec (repeat x [])) size (count s) i 0]
    (if (= size i)
      res
      (let [k (mod i x)
            v (nth res k)
            v (conj v (nth s i))
            res (assoc res k v)]
        (recur res size (inc i))))))