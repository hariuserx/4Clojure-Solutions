(fn intervals [lis]
  (let [sor-lis (sort lis)]
    (if (empty? sor-lis)
      []
      (:res (reduce (fn [{:keys [f l res]} val]
                      (prn f l val res)
                      (if (<= (- val l) 1)
                        {:f   f
                         :l   val
                         :res res}
                        {:f   val
                         :l   val
                         :res (conj res [f l])}))
                    {:f   (first sor-lis)
                     :l   (first sor-lis)
                     :res []}
                    (conj (vec (drop 1 sor-lis)) 10000))))))