(fn re-parent-tree [new-root tree]
  (let [get-connections         (fn get-connections [res node]
                                  (let [root     (first node)
                                        children (vec (map first (rest node)))
                                        res      (conj res [root children])]
                                    (reduce (fn [res child]
                                              (get-connections res child))
                                            res
                                            (rest node))))
        root                    (first tree)
        connections             (get-connections [] tree)
        parents                 (reduce (fn [res conn]
                                          (reduce (fn [res child]
                                                    (assoc res child (first conn)))
                                                  res
                                                  (second conn)))
                                        {}
                                        connections)
        connections             (into (sorted-map) connections)
        _                       (prn parents)
        _                       (prn root)
        _                       (prn connections)
        re-parented-connections (loop [ans          connections
                                       current-node new-root]
                                  (let [cnp (get parents current-node)]
                                    (if cnp
                                      (recur (-> ans
                                                 (assoc current-node (conj (get ans current-node) cnp))
                                                 (assoc cnp (vec (remove #(= % current-node) (get ans cnp)))))
                                             cnp)
                                      ans)))
        rpt                     (fn rpt [res node]
                                  (let [res      (conj res node)
                                        children (get re-parented-connections node)]
                                    (reduce (fn [res child]
                                              (conj res (rpt [] child)))
                                            res
                                            children)))]
    (rpt [] new-root)))