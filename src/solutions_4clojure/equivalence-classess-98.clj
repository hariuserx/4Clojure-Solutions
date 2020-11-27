(fn [f s]
  (set (vals ((fn [f s]
                (reduce #(assoc %1
                           (f %2)
                           (conj (get %1 (f %2) #{})
                                 %2))
                        {}
                        s)) f s))))