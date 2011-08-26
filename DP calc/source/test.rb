Dir['**/*.java'].each do |r|
	puts r
	File.open(r).each do |l|
		arr = l.scan(/.*\{(.*)\}.*/)
		if arr != nil
			puts arr
		end
	end
end
