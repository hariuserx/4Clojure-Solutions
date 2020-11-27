(fn universal-computation-engine [formula]
  (fn [m]
    (let [operators {'/ /
                     '+ +
                     '- -
                     '* *}]
      ((fn compute-form [form]
         (let [{operator :operator
                operands :operands} (reduce #(if (sequential? %2)
                                               (assoc
                                                 %1
                                                 :operands
                                                 (conj (:operands %1)
                                                       (compute-form %2)))
                                               (if (contains? operators %2)
                                                 (assoc %1
                                                   :operator
                                                   (get operators %2))
                                                 (assoc
                                                   %1
                                                   :operands
                                                   (conj (:operands %1)
                                                         (if (number? %2)
                                                           %2
                                                           (get m %2))))))
                                            {:operands []
                                             :operator nil}
                                            form)]
           (apply operator operands))) formula))))