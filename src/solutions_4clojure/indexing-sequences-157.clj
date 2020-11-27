#(loop [res [] i 0 size (count %1) s %1]
   (if (= size i)
     res
     (recur (conj res [(nth s i) i]) (inc i) size s)))