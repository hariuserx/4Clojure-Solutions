(fn [& args]
  (let [freqs (frequencies args)
        trues (get freqs true)]
    (if (and trues (< trues (count args)))
      true
      false)))