(fn merge-with-custom [f m1 & maps]
  (reduce (fn [res mi]
            (reduce (fn [acc [k v]]
                      (if (contains? acc k)
                        (assoc acc k (f (get acc k) v))
                        (assoc acc k v))) res mi))
          m1 maps))