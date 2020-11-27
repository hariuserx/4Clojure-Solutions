(fn [m] (reduce (fn [res x]
                  (assoc res x (inc (or (get res x) 0)))) {}  m))