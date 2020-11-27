(fn [sequence n]
  (loop [res [] remaining-seq sequence]
    (if (< (count remaining-seq) n)
      (concat res remaining-seq)
      (recur (concat res (take (dec n) remaining-seq)) (drop n remaining-seq)))))