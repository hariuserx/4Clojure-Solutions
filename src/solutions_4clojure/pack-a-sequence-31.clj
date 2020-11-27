(fn [sequence] (loop [res [] i 0 size (count sequence)]
                 (if (= size i)
                   res
                   (let [curr_val     (nth sequence i)
                         previous_vec (or (last res) [])
                         previous_val (first previous_vec)
                         res          (if (= previous_val curr_val)
                                        (conj (pop res) (conj previous_vec curr_val))
                                        (conj res [curr_val]))]
                     (recur res
                            (inc i)
                            size)))))