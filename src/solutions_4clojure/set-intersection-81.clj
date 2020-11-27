(fn [a b]
  (set (filter #(contains? b %) a)))