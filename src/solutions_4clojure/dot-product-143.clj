#(loop [sum 0
        i 0
        size (count %1)]
   (if (= i size)
     sum
     (recur (+ sum (* (nth %1 i) (nth %2 i))) (inc i) size)))